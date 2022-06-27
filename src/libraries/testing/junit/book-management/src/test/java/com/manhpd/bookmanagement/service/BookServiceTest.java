package com.manhpd.bookmanagement.service;

import com.manhpd.bookmanagement.entity.Book;
import com.manhpd.bookmanagement.exception.NotFoundBookException;
import com.manhpd.bookmanagement.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void whenGetAll_shouldReturnList() {
        List<Book> mockedBooks = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            mockedBooks.add(new Book((long) i));
        }

        when(this.bookRepository.findAll()).thenReturn(mockedBooks);

        List<Book> actualBooks = this.bookService.getAllBooks();
        Assertions.assertThat(actualBooks.size()).isEqualTo(mockedBooks.size());

        // ensure repository is called
        verify(this.bookRepository).findAll();
    }

    @Test
    public void whenGetInvalidOne_shouldThrowException() {
        Long invalidId = 7L;

        when(this.bookRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

        Assertions.assertThatThrownBy(() -> this.bookService.getOne(invalidId))
                  .isInstanceOf(NotFoundBookException.class);

        verify(this.bookRepository).findById(any(Long.class));
    }

}