package ro.ubb.movieapp.web.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.model.ResponseMessage;
import ro.ubb.movieapp.core.service.ClientService;
import ro.ubb.movieapp.core.service.MovieServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UploadController {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private ClientService clientService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            if (Objects.equals(file.getOriginalFilename(), "clients.csv")) {
                BufferedReader br;
                List<String> result = new ArrayList<>();
                try {

                    String line;
                    InputStream is = file.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {

                        result.add(line);
                        System.out.println(line);
                    }
                    System.out.println(result);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (Objects.equals(file.getOriginalFilename(), "movies.csv")) {
                BufferedReader br;
                List<String> result = new ArrayList<>();
                try {

                    String line;
                    InputStream is = file.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {

                        result.add(line);
                        String[] splitLine = line.split(",");

                    }
                    System.out.println(result);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (Objects.equals(file.getOriginalFilename(), "addresses.csv")) {
                BufferedReader br;
                List<String> result = new ArrayList<>();
                try {

                    String line;
                    InputStream is = file.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {

                        result.add(line);
                    }
                    System.out.println(result);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



}
