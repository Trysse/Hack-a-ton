package com.example.hackaton;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;

import dev.langchain4j.data.document.loader.UrlDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Configuration
public class ParsingConfiguration {
    @Bean
    public ApacheTikaDocumentParser apacheTikaDocumentParser() {
        return new ApacheTikaDocumentParser();
    }

    @Bean
    public ContentRetriever contentRetriever(ApacheTikaDocumentParser apacheTikaDocumentParser) {
        // Here, we create an empty in-memory store for our documents and their embeddings.
//        InMemoryEmbeddingStore<TextSegment> embeddingStore = InMemoryEmbeddingStore.fromFile("vector-db.txt");
//        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

//        log.info("Start ingesting the document");
//        String path = ParsingConfiguration.class.getClassLoader().getResource("medical-tests-catalog.pdf").getPath();
//        Document document = FileSystemDocumentLoader.loadDocument(path, apacheTikaDocumentParser);
//        // Here, we are ingesting our documents into the store.
//        // Under the hood, a lot of "magic" is happening, but we can ignore it for now.
//        EmbeddingStoreIngestor.ingest(document, embeddingStore);
//        embeddingStore.serializeToFile("vector-db.txt");
//        log.info("Done ingesting the document");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        List<String> analyses = List.of("creatine-kinase", "ferritin-blood-test", "glucose-in-urine-test");
//        List<String> analyses = List.of("ferritin-blood-test");
        for (String analyse : analyses) {
            Document document = UrlDocumentLoader.load("https://medlineplus.gov/lab-tests/" + analyse + "/", apacheTikaDocumentParser);
            // Here, we are ingesting our documents into the store.
            // Under the hood, a lot of "magic" is happening, but we can ignore it for now.
            EmbeddingStoreIngestor.ingest(document, embeddingStore);

        }



// Lastly, let's create a content retriever from an embedding store.
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
//

}
