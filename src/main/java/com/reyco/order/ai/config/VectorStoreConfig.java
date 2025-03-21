package com.reyco.order.ai.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 向量数据库
 * @author reyco
 *
 */
@Configuration
public class VectorStoreConfig {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public VectorStore vectorStore(EmbeddingModel embeddingModel) {
		VectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
		vectorStore.write(new TokenTextSplitter().transform(new TextReader("classpath:file.txt").read()));
		List<Document> document = vectorStore.similaritySearch("预订");
		logger.debug("document:"+document);
		return vectorStore;
	}
}
