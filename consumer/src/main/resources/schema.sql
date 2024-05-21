CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE data_metric_entity (
                                    id UUID PRIMARY KEY,
                                    metric_name VARCHAR(255) NOT NULL,
                                    measurement DOUBLE PRECISION NOT NULL,
                                    created_at TIMESTAMP NOT NULL
);

CREATE TABLE data_metric_entity_tags (
                                         data_metric_entity_id UUID NOT NULL,
                                         tags_key VARCHAR(255) NOT NULL,
                                         tags_value VARCHAR(255),
                                         PRIMARY KEY (data_metric_entity_id, tags_key),
                                         CONSTRAINT fk_data_metric_entity
                                             FOREIGN KEY (data_metric_entity_id)
                                                 REFERENCES data_metric_entity (id)
                                                 ON DELETE CASCADE
);
