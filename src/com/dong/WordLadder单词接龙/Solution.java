package com.dong.WordLadder单词接龙;

import javafx.util.Pair;

import java.util.*;

/**
 * https://mp.weixin.qq.com/s/hwTsLyjxI2AZ5I94OAGWnQ
 *
 * @author dongsiyuan
 * @date 2020年9月1日
 */
public class Solution {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        int len = beginWord.length();

        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        //创建邻接链表
        wordList.forEach(curWord -> {
            for (int i = 0; i < len; i++) {
                String comboWord = curWord.substring(0, i) + "*" + curWord.substring(i + 1, len);
                ArrayList<String> comboWordList = allComboDict.getOrDefault(comboWord, new ArrayList<>());
                comboWordList.add(curWord);
                allComboDict.put(comboWord, comboWordList);
            }
        });

        // 广度优先遍历队列
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        HashMap<String, Boolean> hasVistedList = new HashMap<>();

        queue.add(new Pair<>(beginWord, 1));

        hasVistedList.put(beginWord, true);

        // 广度优先遍历，逐个取出队列中元素进行操作
        while (!queue.isEmpty()) {

            Pair<String, Integer> node = queue.remove();
            String currWord = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < len; i++) {
                String currComboWord = currWord.substring(0, i) + "*" + currWord.substring(i + 1, len);
                ArrayList<String> currComboWordList = allComboDict.getOrDefault(currComboWord, new ArrayList<>());

                for (String word : currComboWordList) {
                    if (word.equals(endWord)) {
                        return level + 1;
                    }

                    if (!hasVistedList.containsKey(word)) {
                        queue.add(new Pair<>(word, level + 1));
                        hasVistedList.put(word, true);
                    }
                }
            }
        }
        return 0;
    }
}
