package com.epam.service;

import com.epam.dto.SongDTO;
import com.epam.exception.MimetypeNotValidException;
import com.epam.feign.SongService;
import com.epam.repo.Music;
import com.epam.repo.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private static final Tika tika = new Tika();
    private final MusicRepository musicRepository;

    private final SongService songService;

    @Override
    public int upload(MultipartFile audioFile) throws IOException {
        validateFile(audioFile);
        var music  = new Music();
        music.setData(audioFile.getBytes());//TODO: check for storing bytes
        music.setName(audioFile.getName());
        musicRepository.save(music);
        try {
            saveMetadata(audioFile, music.getId());
        } catch (Exception ex) {
            log.error("some error", ex);
            musicRepository.delete(music);
            throw new RuntimeException("failed to save metadata");
        }
        return music.getId();
    }

    @Override
    public Music get(Integer id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The resource with the specified id does not exist"));
    }

    @Override
    public List<Integer> delete(List<Integer> ids) {
        musicRepository.deleteByIdIn(ids);
        List<Integer> idsLeft = musicRepository.findIdsIn(ids);
        ids.removeAll(idsLeft);
        return ids;
    }

    private void validateFile(MultipartFile audioFile) throws IOException {
        String mimetype = "";
        try(InputStream inputStream = audioFile.getInputStream()) {
            mimetype = tika.detect(inputStream);
            if (!mimetype.equals("audio/mpeg"))
                throw new MimetypeNotValidException("Validation failed or request body is invalid MP3");
        }
    }

    private void saveMetadata(MultipartFile audioFile, Integer resourceId) throws Exception {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();

        Mp3Parser mp3Parser = new  Mp3Parser();
        ParseContext pcontext = new ParseContext();

        try(var inputStream = audioFile.getInputStream()) {
            mp3Parser.parse(inputStream, handler, metadata, pcontext);
        }

        String[] metadataNames = metadata.names();
        var songDTO = new SongDTO();
        for(String name : metadataNames){
            String meta = metadata.get(name);
            if("xmpDM:artist".equals(name)) songDTO.setArtist(meta);
            if("dc:title".equals(name)) songDTO.setName(name);
            if("xmpDM:releaseDate".equals(name)) songDTO.setYear(meta);
            if("xmpDM:album".equals(name)) songDTO.setAlbum(meta);
            if("xmpDM:duration".equals(name)) songDTO.setLength(meta);
        }
        songDTO.setResourceId(resourceId);
        songService.saveMetadata(songDTO);
    }
}
