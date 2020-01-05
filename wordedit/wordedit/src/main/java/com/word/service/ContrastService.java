package com.word.service;

import java.util.List;

import com.word.pojo.Contrastlibrary;

public interface ContrastService {

	public List<Contrastlibrary> findAll();

	public void addcontrast(Contrastlibrary contrast);

	public List<Contrastlibrary> findbyuid(String uid);

	public void deletecontrast(String uid);
}
