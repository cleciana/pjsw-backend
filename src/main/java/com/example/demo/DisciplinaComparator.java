package com.example.demo;

import java.util.Comparator;

import com.example.demo.rest.model.Disciplina;

public class DisciplinaComparator<T> implements Comparator<Disciplina> {

	@Override
	public int compare(Disciplina o1, Disciplina o2) {
		return o1.compareTo(o2);
	}

}
