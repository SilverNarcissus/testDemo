package myUtil;

/**
 * Created by SilverNarcissus on 2018/2/23.
 */
public class Trie {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode temp = root;

        for (char c : word.toCharArray()) {
            temp = temp.addWord(c);
        }

        temp.setWord();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode temp = root;

        for (char c : word.toCharArray()) {
            temp = temp.getNode(c);
            if (temp == null) {
                return false;
            }
        }

        return temp.isWord();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode temp = root;


        for (char c : prefix.toCharArray()) {
            temp = temp.getNode(c);
            if (temp == null) {
                return false;
            }
        }

        return true;
    }

    private class TrieNode {
        private TrieNode[] nodes;
        private boolean isWord;

        private TrieNode() {
            nodes = new TrieNode[26];
        }

        private TrieNode addWord(char c) {
            if (nodes[c - 'a'] == null) {
                nodes[c - 'a'] = new TrieNode();
            }

            return nodes[c - 'a'];
        }

        private TrieNode getNode(char c) {
            return nodes[c - 'a'];
        }

        private void setWord() {
            isWord = true;
        }

        private boolean isWord() {
            return isWord;
        }
    }
}