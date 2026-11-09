package com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.*;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollectionMapper {

    // ---------------------------------------------- entity -> response

    public ItemResponseModel itemToResponseModel(LibraryItem item) {

        if (item == null) {
            return null;
        }

        ItemResponseModel model = new ItemResponseModel();

        model.setItemId(item.getItemId() == null
                ? null : item.getItemId().toString());
        model.setTitle(item.getTitle());
        model.setDescription(item.getDescription()); // polymorphic call

        if (item.getPublicationYear() != null) {
            model.setPublicationYear(item.getPublicationYear().getYear());
        }
        if (item.getStatus() != null) {
            model.setStatus(item.getStatus().name());
        }

        if (item instanceof Book book) {
            model.setType("BOOK");
            if (book.getISBN() != null) {
                model.setIsbn(book.getISBN().getValue());
            }
            model.setAuthors(book.getAuthors().stream()
                    .map(this::authorToSummaryModel)
                    .collect(Collectors.toList()));
        }
        else if (item instanceof AudioMaterial audio) {
            model.setType("AUDIO");
            if (audio.getDuration() != null) {
                model.setDurationMinutes(audio.getDuration().getMinutes());
            }
            model.setNarrator(audio.getNarrator());
        }
        else if (item instanceof VideoMaterial video) {
            model.setType("VIDEO");
            if (video.getDuration() != null) {
                model.setDurationMinutes(video.getDuration().getMinutes());
            }
            if (video.getFormat() != null) {
                model.setFormat(video.getFormat().name());
            }
            model.setAvgRating(video.getAvgRating());
        }

        return model;
    }

    public List<ItemResponseModel> itemListToResponseModelList(
            List<LibraryItem> items) {
        if (items == null) {
            return List.of();
        }
        return items.stream()
                .map(this::itemToResponseModel)
                .collect(Collectors.toList());
    }

    public AuthorSummaryModel authorToSummaryModel(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorSummaryModel(
                author.getAuthorId() == null
                        ? null : author.getAuthorId().toString(),
                author.getName());
    }

    public List<AuthorSummaryModel> authorListToSummaryModelList(
            List<Author> authors) {
        if (authors == null) {
            return List.of();
        }
        return authors.stream()
                .map(this::authorToSummaryModel)
                .collect(Collectors.toList());
    }

    public AuthorResponseModel authorToResponseModel(Author author) {

        if (author == null) {
            return null;
        }

        AuthorResponseModel model = new AuthorResponseModel();

        model.setAuthorId(author.getAuthorId() == null
                ? null : author.getAuthorId().toString());
        model.setName(author.getName());

        if (author.getAddress() != null) {
            model.setStreet(author.getAddress().getStreet());
            model.setProvince(author.getAddress().getProvince());
            model.setCity(author.getAddress().getCity());
            model.setPostalCode(author.getAddress().getPostalCode());
        }

        if (author.getPhoneNumbers() != null) {
            model.setPhoneNumbers(author.getPhoneNumbers().stream()
                    .map(PhoneNumber::getValue)
                    .collect(Collectors.toList()));
        }

        return model;
    }

    public List<AuthorResponseModel> authorListToResponseModelList(
            List<Author> authors) {
        if (authors == null) {
            return List.of();
        }
        return authors.stream()
                .map(this::authorToResponseModel)
                .collect(Collectors.toList());
    }

    // ---------------------------------------------- request -> entity

    public Book requestModelToBook(BookRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }
        return new Book(
                requestModel.getTitle(),
                requestModel.getPublicationYear() == null
                        ? null
                        : new PublicationYear(
                        requestModel.getPublicationYear()),
                new ISBN(requestModel.getIsbn()));
        // authors are NOT set here: the service resolves them by id.
    }

    public AudioMaterial requestModelToAudioMaterial(
            AudioMaterialRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }
        return new AudioMaterial(
                requestModel.getTitle(),
                requestModel.getPublicationYear() == null
                        ? null
                        : new PublicationYear(
                        requestModel.getPublicationYear()),
                requestModel.getDurationMinutes() == null
                        ? null
                        : new Duration(requestModel.getDurationMinutes()),
                requestModel.getNarrator());
    }

    public VideoMaterial requestModelToVideoMaterial(
            VideoMaterialRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }
        return new VideoMaterial(
                requestModel.getTitle(),
                requestModel.getPublicationYear() == null
                        ? null
                        : new PublicationYear(
                        requestModel.getPublicationYear()),
                requestModel.getDurationMinutes() == null
                        ? null
                        : new Duration(requestModel.getDurationMinutes()),
                requestModel.getFormat(),
                requestModel.getAvgRating());
    }

    public Author requestModelToAuthor(AuthorRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }
        Author author = new Author(
                requestModel.getName(),
                new Address(
                        requestModel.getStreet(),
                        requestModel.getProvince(),
                        requestModel.getCity(),
                        requestModel.getPostalCode()));

        if (requestModel.getPhoneNumbers() != null) {
            requestModel.getPhoneNumbers().stream()
                    .filter(p -> p != null && !p.isBlank())
                    .map(PhoneNumber::new)
                    .forEach(author::addPhoneNumber);
        }

        return author;
    }
}
