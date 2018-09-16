
CREATE TABLE IF NOT EXISTS numbers_addons(
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	number_id UUID NOT NULL REFERENCES numbers(id),
	addon_id UUID NOT NULL REFERENCES addons(id)
);
