package Actors;

import Components.BookItem;
import Models.Person;

public class Librarian extends Account{
    public Librarian(int id, String password, String status, Person person) {
        super(id, password, status, person);
    }
    public void add_book(BookItem bookItem){
        System.out.println("Book "+bookItem+" added");
    }
    public void blockMember(Member member){
        System.out.println("Member "+member.getPerson().getName()+ " blocked");
    }
    public void unblockMember(Member member){
        System.out.println("Member "+member.getPerson().getName()+ " unblocked");
    }
}
