package com.ccc.deeplearning.word2vec.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jblas.DoubleMatrix;

import com.ccc.deeplearning.berkeley.Counter;
import com.ccc.deeplearning.word2vec.Word2Vec;

public class WordMetaData {
	private String words;
	private Counter<String> wordCounts;
	private List<String> wordList;
	private Word2Vec vec;
	
	public WordMetaData(Word2Vec vec,String words) {
		this.words = words;
		this.vec = vec;
		this.wordCounts = new Counter<String>();
		wordList = new ArrayList<String>();
	}

	public DoubleMatrix getVectorForWord(String word) {
		return vec.getWordVectorMatrix(word).mul(wordCounts.getCount(word));
	}

	private void addWords(String words) {
		StringTokenizer t1 = new StringTokenizer(words);
		while(t1.hasMoreTokens()) {
			String next = t1.nextToken();
			if(!wordList.contains(next) && vec.hasWord(next)) {
				wordList.add(next);
			}
			if(vec.hasWord(next))
				wordCounts.incrementCount(next, 1.0);
		}
	}

	public void calc() {
		addWords(words);
	}

	public String getWords() {
		return words;
	}

	public Counter<String> getWordCounts() {
		return wordCounts;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public Word2Vec getVec() {
		return vec;
	}

}
