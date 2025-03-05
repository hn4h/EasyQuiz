/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author 11
 */
public class QuizSetDetail {
    private QuizSet qs;
    private List<FlashCard> flashCards;

    public QuizSetDetail() {
    }

    public QuizSetDetail(QuizSet qs, List<FlashCard> flashCards) {
        this.qs = qs;
        this.flashCards = flashCards;
    }

    public QuizSet getQs() {
        return qs;
    }

    public void setQs(QuizSet qs) {
        this.qs = qs;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }
    
}
