package com.gl.lms.service;

import com.gl.lms.dto.AuthorsDTO;
import com.gl.lms.dto.ResponseDTO;
import com.gl.lms.dto.ReviewsDTO;
import com.gl.lms.dto.UsersDTO;
import com.gl.lms.entity.Books;
import com.gl.lms.entity.Reviews;
import com.gl.lms.exception.LibraryManagementSystemException;

import java.util.List;

public interface LibraryManagementSystemService {
    public ResponseDTO addUserAndIssueLibraryCard(UsersDTO usersDTO) throws LibraryManagementSystemException;
    public UsersDTO fetchUserAndIssuedLibraryCardByEmail(String email) throws LibraryManagementSystemException;
    public ResponseDTO updateNameOfUser (String email,String updateName ) throws LibraryManagementSystemException;
    public ResponseDTO deleteUserAndAssociatedLibraryCard(String email) throws LibraryManagementSystemException;
    public ResponseDTO addAuthorAndItsBooks(AuthorsDTO authorDTO) throws LibraryManagementSystemException;
    public ResponseDTO addReviewToBook(String bookTitle, ReviewsDTO reviewsDTO) throws LibraryManagementSystemException;
    public List<ReviewsDTO> getAllReviewsOfBook(String bookTitle) throws LibraryManagementSystemException;
    public ResponseDTO delectBookAndAssociatedReviews(String bookTitle) throws LibraryManagementSystemException;
}
