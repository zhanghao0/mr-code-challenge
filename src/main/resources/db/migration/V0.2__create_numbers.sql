
CREATE TABLE IF NOT EXISTS numbers(
	id UUID PRIMARY KEY,
	number TEXT NOT NULL,
	status TEXT NOT NULL,
	last_updated_by TEXT,
	last_updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
	version INT8 NOT NULL DEFAULT 1
);

ALTER TABLE IF EXISTS numbers
	DROP CONSTRAINT IF EXISTS numbers_number_ui,
	ADD CONSTRAINT numbers_number_ui UNIQUE(number);
