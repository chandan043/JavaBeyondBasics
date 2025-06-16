package com.gl.lms.service;

import com.gl.lms.dto.*;
import com.gl.lms.entity.*;
import com.gl.lms.exception.LibraryManagementSystemException;
import com.gl.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryManagementSystemServiceImpl implements LibraryManagementSystemService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthorsRepository authorsRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private LibraryCardsRepository libraryCardsRepository;
  

    @Override
    public ResponseDTO addUserAndIssueLibraryCard(UsersDTO usersDTO) throws LibraryManagementSystemException {
        Users user = usersRepository.findByEmail(usersDTO.getEmail());
        if(user!=null)
            throw new LibraryManagementSystemException("User is already Exist!");
        user = new Users();
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());

        LibraryCards libraryCards = new LibraryCards();

        libraryCards.setIssueDate(usersDTO.getLibraryCardsDTO().getIssueDate());
        libraryCards.setExpiryDate(usersDTO.getLibraryCardsDTO().getExpiryDate());

        user.setLibraryCards(libraryCards);

       usersRepository.save(user);

       ResponseDTO responseDTO = new ResponseDTO();

       responseDTO.setMessage("User is added Successfully with User's ID:"+user.getId()+"and library Card"+user.getLibraryCards().getId()+"!");

        return responseDTO;
    }

    @Override
    public UsersDTO fetchUserAndIssuedLibraryCardByEmail(String email) throws LibraryManagementSystemException {
        Users user = usersRepository.findByEmail(email);
        if(user==null)
            throw new LibraryManagementSystemException("User Does not Exist!");
        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setId(user.getId());
        usersDTO.setEmail(user.getEmail());
        usersDTO.setName(user.getName());

        LibraryCardsDTO libraryCardsDTO = new LibraryCardsDTO();

        libraryCardsDTO.setId(user.getLibraryCards().getId());
        libraryCardsDTO.setIssueDate(user.getLibraryCards().getIssueDate());
        libraryCardsDTO.setExpiryDate(user.getLibraryCards().getExpiryDate());

        usersDTO.setLibraryCardsDTO(libraryCardsDTO);

        return usersDTO;
    }

    @Override
    public ResponseDTO updateNameOfUser(String email, String updateName) throws LibraryManagementSystemException {
        Users user = usersRepository.findByEmail(email);
        if(user==null)
            throw new LibraryManagementSystemException("User Does Not Exist!");
        String oldName = user.getName();
        user.setName(updateName);
        usersRepository.save(user);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Updated name of user from "+oldName+" to "+updateName);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteUserAndAssociatedLibraryCard(String email) throws LibraryManagementSystemException {
        Users user =usersRepository.findByEmail(email);
        if(user==null)
            throw new LibraryManagementSystemException("User Does Not Exist!");
        usersRepository.delete(user);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("User Sucessfully Deleted with Associated Library Card");
        return responseDTO;
    }

    @Override
    public ResponseDTO addAuthorAndItsBooks(AuthorsDTO authorDTO) throws LibraryManagementSystemException {
        Authors existingAuthor = authorsRepository.findByName(authorDTO.getName());
        if (existingAuthor != null) {
            throw new LibraryManagementSystemException("Author already exists!");
        }

        Authors author = new Authors();
        author.setName(authorDTO.getName());
        author.setId(authorDTO.getId());

        List<Books> booksList = new ArrayList<>();
        if (authorDTO.getBooksDTOs() != null) {
            for (BooksDTO booksDTO : authorDTO.getBooksDTOs()) {
                Books book = new Books();
                book.setTitle(booksDTO.getTitle());
                book.setId(booksDTO.getId());

                booksList.add(book);
            }
        }

        author.setBooks(booksList);

        authorsRepository.save(author);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Added Author and Its Books Successfully");
        return responseDTO;
    }


    @Override
    public ResponseDTO addReviewToBook(String bookTitle, ReviewsDTO reviewsDTO) throws LibraryManagementSystemException {
        Books book = booksRepository.findByTitle(bookTitle);
        if(book==null)
            throw new LibraryManagementSystemException("book not found!");

        Reviews review = new Reviews();
        review.setId(reviewsDTO.getId());
        review.setComment(reviewsDTO.getComment());
        review.setRating(reviewsDTO.getRating());

        review.setBook(book);

        reviewsRepository.save(review);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Added Review to book sucessfully!");
        return responseDTO;

    }

    @Override
    public List<ReviewsDTO> getAllReviewsOfBook(String bookTitle) throws LibraryManagementSystemException {
        Books book = booksRepository.findByTitle(bookTitle);
        if(book==null)
            throw new LibraryManagementSystemException("book not found!");
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setId(book.getId());
        booksDTO.setTitle(book.getTitle());

        List<ReviewsDTO> reviewsList = new ArrayList<>();
        for(Reviews review : reviewsRepository.findByBook(book)) {
            ReviewsDTO reviews = new ReviewsDTO();

            reviews.setRating(review.getRating());
            reviews.setComment(review.getComment());
            reviews.setId(review.getId());
            reviews.setBooksDTO(booksDTO);

            reviewsList.add(reviews);
        }

        return reviewsList;
    }

    @Override
    public ResponseDTO delectBookAndAssociatedReviews(String bookTitle) throws LibraryManagementSystemException {
        Books book = booksRepository.findByTitle(bookTitle);
        if(book==null)
            throw new LibraryManagementSystemException("book not found!");

        List<Reviews> reviews = reviewsRepository.findByBook(book);

        reviewsRepository.deleteAll(reviews);
        booksRepository.delete(book);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Deleted Book And Associated Reviews!");
        return responseDTO;

    }
}
