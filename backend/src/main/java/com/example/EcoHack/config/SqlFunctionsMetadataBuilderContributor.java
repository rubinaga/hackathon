package com.example.EcoHack.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("fts",
                new StandardSQLFunction("search_vector @@ to_tsquery(regexp_replace(cast(plainto_tsquery(f_unaccent(?1)) as text), E'(\\'\\\\w+\\')', E'\\\\1:*', 'g'))", StandardBasicTypes.BOOLEAN));
        metadataBuilder.applySqlFunction("custom_ts_rank",
                new StandardSQLFunction("ts_rank(search_vector, plainto_tsquery(f_unaccent(?1)))", StandardBasicTypes.BOOLEAN));
    }
}
