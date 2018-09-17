CREATE TABLE IF NOT EXISTS numbers(
	id UUID PRIMARY KEY,
	number VARCHAR(56) NOT NULL,
	status VARCHAR(50) NOT NULL,
	last_updated_by VARCHAR(50),
	last_updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	version INT8 NOT NULL DEFAULT 1
);

ALTER TABLE IF EXISTS numbers
	DROP CONSTRAINT IF EXISTS numbers_number_ui;
	
ALTER TABLE IF EXISTS numbers
	ADD CONSTRAINT numbers_number_ui UNIQUE(number);


CREATE TABLE IF NOT EXISTS events(
	id UUID PRIMARY KEY,
	type VARCHAR(50) NOT NULL,
	number_id UUID NOT NULL REFERENCES numbers(id),
	parameter VARCHAR(255) NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE INDEX events_number_id_idx ON events(number_id);



CREATE TABLE IF NOT EXISTS addons(
	id UUID PRIMARY KEY,
	type VARCHAR(50) NOT NULL,
	cost INT4 NOT NULL
);



CREATE TABLE IF NOT EXISTS numbers_addons(
	id UUID PRIMARY KEY,
	number_id UUID NOT NULL,
	addon_id UUID NOT NULL,
	foreign key (number_id) references numbers(id),
	foreign key (addon_id) references addons(id)
);


ALTER TABLE numbers_addons 
	ALTER COLUMN id SET DEFAULT RANDOM_UUID();

	