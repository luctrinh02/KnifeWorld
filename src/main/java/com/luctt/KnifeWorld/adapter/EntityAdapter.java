package com.luctt.KnifeWorld.adapter;

public interface EntityAdapter<E,D> {
	D entityToDto(E e);
}
