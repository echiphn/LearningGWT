package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.Controller;
import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Author;
import com.manning.gwtip.bookstore.client.model.Book;
import com.manning.gwtip.bookstore.client.model.Category;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.List;


public class BookEdit extends Composite {
    private static final BookstoreConstants CONSTANTS = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private AuthorSelect auths = new AuthorSelect();
    private Book book;
    private Button newAuthor = new Button(CONSTANTS.create());
    private Button newCategory = new Button(CONSTANTS.create());
    private Button newImage = new Button(CONSTANTS.create());
    private Button save = new Button(CONSTANTS.save());
    private CategorySelect cats = new CategorySelect();
    private HorizontalPanel catsAndAuthors = new HorizontalPanel();
    private Image cover = new Image();
    private TextArea description = new TextArea();
    private TextBox imageUrl = new TextBox();
    private TextBox title = new TextBox();
    private VerticalPanel authorPanel = new VerticalPanel();
    private VerticalPanel catPanel = new VerticalPanel();
    private VerticalPanel main = new VerticalPanel();
    private PropertyChangeListener[] listeners = new PropertyChangeListener[3];

    public BookEdit(final Book book) {
        this.book = book;
        main.add(new Label(CONSTANTS.title()));
        main.add(title);
        title.setText(book.getTitle());
        main.add(new Label(CONSTANTS.description()));
        main.add(description);
        description.setText(book.getDescription());
        catPanel.add(new Label(CONSTANTS.categories()));
        catPanel.add(newCategory);
        catPanel.add(cats);
        catsAndAuthors.add(catPanel);
        authorPanel.add(new Label(CONSTANTS.authors()));
        authorPanel.add(newAuthor);
        authorPanel.add(auths);
        catsAndAuthors.add(authorPanel);

        main.add(catsAndAuthors);

        cats.setSelectedCategories(book.getCategories());
        auths.setSelectedAuthors(book.getAuthors());
        main.add(new Label(CONSTANTS.cover()));
        
        if (book.getImage() == null)
        {
            cover.setUrl(GWT.getModuleBaseURL() + "nocover.gif");
        }    
        else if (!book.getImage().startsWith(GWT.getModuleBaseURL()))
        {
            cover.setUrl(GWT.getModuleBaseURL() + book.getImage());
        }
        
        main.add(cover);
        main.add(newImage);

        main.add(save);

        save.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    Controller.getInstance().storeBook(book);
                }
            });

        newCategory.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    final DialogBox box = new DialogBox();
                    box.setPopupPosition(widget.getAbsoluteLeft(),
                        widget.getAbsoluteTop());
                    box.setText(CONSTANTS.categoryName());

                    VerticalPanel edit = new VerticalPanel();
                    final TextBox name = new TextBox();
                    Button save = new Button(CONSTANTS.save());
                    edit.add(name);

                    HorizontalPanel buttons = new HorizontalPanel();
                    buttons.add(save);
                    buttons.add(new Button(CONSTANTS.cancel(),
                            new ClickListener() {
                            public void onClick(Widget widget) {
                                box.hide();
                            }
                        }));
                    edit.add(buttons);

                    final Category c = new Category();
                    name.addChangeListener(new ChangeListener() {
                            public void onChange(Widget widget) {
                                c.setName(name.getText());
                            }
                        });
                    save.addClickListener(new ClickListener() {
                            public void onClick(Widget widget) {
                                List selected = new ArrayList(cats.getSelectedCategories());
                                selected.add(c);

                                List catlist = ModelState.getInstance()
                                                         .getCategories();

                                if(catlist == null) {
                                    catlist = new ArrayList();
                                }

                                catlist.add(c);

                                ModelState.getInstance().setCategories(catlist);

                                cats.setSelectedCategories(selected);
                                book.setCategories(selected);
                                box.hide();
                            }
                        });
                    box.setWidget(edit);
                    box.show();
                }
            });

        newAuthor.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    final DialogBox box = new DialogBox();
                    box.setPopupPosition(widget.getAbsoluteLeft(),
                        widget.getAbsoluteTop());
                    box.setText(CONSTANTS.author());

                    VerticalPanel edit = new VerticalPanel();
                    final AuthorEdit authEdit = new AuthorEdit(new Author());
                    Button save = new Button(CONSTANTS.save());
                    edit.add(authEdit);

                    HorizontalPanel buttons = new HorizontalPanel();
                    buttons.add(save);
                    buttons.add(new Button(CONSTANTS.cancel(),
                            new ClickListener() {
                            public void onClick(Widget widget) {
                                box.hide();
                            }
                        }));
                    edit.add(buttons);

                    save.addClickListener(new ClickListener() {
                            public void onClick(Widget widget) {
                                List selected = new ArrayList(auths.getSelectedAuthors());
                                selected.add(authEdit.getAuthor());

                                List authList = ModelState.getInstance()
                                                          .getAuthors();

                                if(authList == null) {
                                    authList = new ArrayList();
                                }

                                authList.add(authEdit.getAuthor());

                                ModelState.getInstance().setAuthors(authList);

                                auths.setSelectedAuthors(selected);
                                book.setAuthors(selected);
                                box.hide();
                            }
                        });
                    box.setWidget(edit);
                    box.show();
                }
            });

        newImage.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    final DialogBox box = new DialogBox();
                    box.setPopupPosition(widget.getAbsoluteLeft(),
                        widget.getAbsoluteTop());
                    box.setText(CONSTANTS.cover());

                    final FormPanel form = new FormPanel();
                    form.setAction(GWT.getModuleBaseURL() + "CoverUpload");
                    form.setEncoding(FormPanel.ENCODING_MULTIPART);
                    form.setMethod(FormPanel.METHOD_POST);
                    form.addFormHandler(new FormHandler() {
                            public void onSubmitComplete(
                                FormSubmitCompleteEvent completeEvent) {
                                String url = completeEvent.getResults();

                                if(url.indexOf("<pre>") != -1) {
                                    url = url.substring(url.indexOf(">") + 1,
                                            url.lastIndexOf("<"));
                                }
                                
                                book.setImage(GWT.getModuleBaseURL() + ".." +
                                    url);
                                cover.setUrl(book.getImage());
                               
                                box.hide();
                            }

                            public void onSubmit(FormSubmitEvent submit) {
                                ;
                            }
                        });

                    VerticalPanel inner = new VerticalPanel();
                    form.setWidget(inner);

                    FileUpload upload = new FileUpload();
                    upload.setName("cover");
                    inner.add(upload);

                    Button save = new Button(CONSTANTS.save(),
                            new ClickListener() {
                                public void onClick(Widget widget) {
                                    form.submit();
                                }
                            });
                    HorizontalPanel buttons = new HorizontalPanel();
                    buttons.add(save);

                    inner.add(buttons);
                    box.setWidget(form);
                    box.show();
                }
            });

        listeners[0] = new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        title.setText((String) propertyChangeEvent.getNewValue());
                    }
                };
        book.addPropertyChangeListener("title", listeners[0]);
        title.addChangeListener(new ChangeListener() {
                public void onChange(Widget sender) {
                    book.setTitle(title.getText());
                }
            });

        listeners[1] = new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        description.setText((String) propertyChangeEvent.getNewValue());
                    }
                };

        book.addPropertyChangeListener("description", listeners[1]);
        description.addChangeListener(new ChangeListener() {
                public void onChange(Widget sender) {
                    book.setDescription(description.getText());
                }
            });

        listeners[2] = new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        cats.setSelectedCategories((List) propertyChangeEvent.getNewValue());
                    }
                };
        book.addPropertyChangeListener("categories", listeners[2]);
        cats.addChangeListener(new ChangeListener() {
                public void onChange(Widget sender) {
                    book.setCategories(cats.getSelectedCategories());
                }
            });
        auths.addChangeListener(new ChangeListener() {
                public void onChange(Widget sender) {
                    book.setAuthors(auths.getSelectedAuthors());
                }
            });

        this.initWidget(main);
        this.setStyleName("bookEdit");
    }

    public void cleanup() {
        for(int i = 0; i < listeners.length; i++) {
            book.removePropertyChangeListener(listeners[i]);
        }

        cats.cleanup();
        auths.cleanup();
    }
}
