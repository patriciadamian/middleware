package com.complany.middleware.service;

import com.complany.middleware.domain.Author;

import java.rmi.RemoteException;
import java.util.List;

public interface IAuthorService {

    Author create(Author author) throws RemoteException;

    Author update(Long authorId, Author authorDetails) throws RemoteException;

    void delete(Long authorId) throws RemoteException;

    List<Author> findByName(String name) throws RemoteException;

    List<Author> getAll() throws RemoteException;
}
