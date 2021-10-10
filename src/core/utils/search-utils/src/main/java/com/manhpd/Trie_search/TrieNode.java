package com.manhpd.Trie_search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrieNode {

    private HashMap<Character, TrieNode> children;

    private String content;

    private boolean isWord;

}
