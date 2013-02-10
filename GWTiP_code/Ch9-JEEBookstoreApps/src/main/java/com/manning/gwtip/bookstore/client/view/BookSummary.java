package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.dnd.DragAndDrop;
import com.manning.gwtip.bookstore.client.model.Author;
import com.manning.gwtip.bookstore.client.model.Book;


public class BookSummary extends FlowPanel {
    private static final BookstoreConstants CONSTANTS = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private Book book;
    CoverImage cover = new CoverImage();
    FlexTable summary = new FlexTable();

    /** Creates a new instance of BookSummary */
    public BookSummary(Book b) {
        super();
        book = b;
        this.add(cover);
        cover.setUrl(b.getImage());
        cover.book = b;
        DragAndDrop.getInstance().makeDraggable(cover, true);
        this.add(summary);
        summary.getFlexCellFormatter().setColSpan(0, 0, 2);
        summary.setWidget(0, 0, cover);
        summary.getFlexCellFormatter().setColSpan(1, 0, 2);
        summary.getFlexCellFormatter()
               .setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER,
            HasVerticalAlignment.ALIGN_TOP);
        summary.setWidget(1, 0, new Label(b.getTitle()));
        summary.setWidget(2, 0, new Label(CONSTANTS.authors()));

        VerticalPanel authors = new VerticalPanel();

        for(int i = 0; (b.getAuthors() != null) && (i < b.getAuthors().size());
                i++) {
            Author a = (Author) b.getAuthors().get(i);
            Label author = new Label(a.getLastName() + ", " + a.getFirstName());
            author.setStyleName( "authorName");
            authors.add(author);
        }

        summary.setWidget(2, 1, authors);
    }

    protected void onAttach() {
        super.onAttach();
    }
}
