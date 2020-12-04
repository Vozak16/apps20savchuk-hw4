package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    static final int MIN_WORD_LENGTH = 3;

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int count = 0;

        for (String string: strings) {

            String[] words = string.split("\\s+");

            for (String word: words) {

                if (!trie.contains(word) && word.length() >= MIN_WORD_LENGTH) {
                    trie.add(new Tuple(word, word.length()));
                    count++;
                }
            }
        }
        return trie.size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {

        if (pref.length() < 2) {
            throw new IllegalArgumentException();
        }

        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {

        List<String> resultList = new ArrayList<String>();
        Iterable<String> initialList = wordsWithPrefix(pref);

        int maxLength;
        int minLength = MIN_WORD_LENGTH;

        if (pref.length() > minLength) {
            minLength = pref.length();
        }
        maxLength = minLength + k - 1;


        for (String elem: initialList) {
            if (elem.length() >= minLength && elem.length() <= maxLength) {
                resultList.add(elem);
            }
        }

        return resultList;
    }

    public int size() {
        return trie.size();
    }
}
