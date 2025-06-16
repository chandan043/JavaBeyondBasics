package com.gl.lms.controller;

import com.gl.lms.dto.AuthorsDTO;
import com.gl.lms.dto.ResponseDTO;
import com.gl.lms.dto.ReviewsDTO;
import com.gl.lms.dto.UsersDTO;
import com.gl.lms.exception.LibraryManagementSystemException;
import com.gl.lms.service.LibraryManagementSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/LMS")
@CrossOrigin
public class libraryManagementSystemController {
    @Autowired
    private LibraryManagementSystemService libraryManagementSystemService;

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> addUserAndIssueLibraryCard(@RequestBody UsersDTO usersDTO)
            throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.addUserAndIssueLibraryCard(usersDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUser/{email}")
    public ResponseEntity<UsersDTO> fetchUserAndIssuedLibraryCardByEmail(@PathVariable String email) throws LibraryManagementSystemException{
        UsersDTO userDTO = libraryManagementSystemService.fetchUserAndIssuedLibraryCardByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/updateName/{email}/{updateName}")
    public ResponseEntity<ResponseDTO> updateNameOfUser(@PathVariable String email,@PathVariable String updateName) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.updateNameOfUser(email,updateName);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<ResponseDTO> deleteUserAndAssociatedLibraryCard(@PathVariable String email) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.deleteUserAndAssociatedLibraryCard(email);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<ResponseDTO> addAuthorAndItsBooks(@RequestBody AuthorsDTO authorDTO) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.addAuthorAndItsBooks(authorDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/addReview/{bookTitle}")
    public ResponseEntity<ResponseDTO> addReviewToBook(@PathVariable String bookTitle, @RequestBody ReviewsDTO reviewsDTO) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.addReviewToBook(bookTitle,reviewsDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getReviews/{bookTitle}")
    public ResponseEntity<List<ReviewsDTO>> getAllReviewsOfBook(@PathVariable String bookTitle) throws LibraryManagementSystemException {
        List<ReviewsDTO> reviewsDTOList = libraryManagementSystemService.getAllReviewsOfBook(bookTitle);
        return new ResponseEntity<>(reviewsDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookTitle}")
    public ResponseEntity<ResponseDTO> delectBookAndAssociatedReviews(@PathVariable String bookTitle) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.delectBookAndAssociatedReviews(bookTitle);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

