
CREATE TABLE IF NOT EXISTS events(
	id UUID PRIMARY KEY,
	type TEXT NOT NULL,
	number_id UUID NOT NULL REFERENCES numbers(id),
	parameter TEXT NOT NULL,
	created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX events_number_id_idx ON events(number_id);
