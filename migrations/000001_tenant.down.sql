-- Drop triggers
DROP TRIGGER IF EXISTS update_users_updated_at ON users;
DROP TRIGGER IF EXISTS update_schools_updated_at ON schools;
DROP TRIGGER IF EXISTS update_tenants_updated_at ON tenants;

-- Drop trigger function
DROP FUNCTION IF EXISTS update_updated_at_column();

-- Drop tables in reverse order to avoid FK constraint issues
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS schools;
DROP TABLE IF EXISTS tenants;

-- Drop enum types
DROP TYPE IF EXISTS school_board;
DROP TYPE IF EXISTS tenant_status;

-- Drop extensions (optional — comment out if you still need them elsewhere)
DROP EXTENSION IF EXISTS citext;
DROP EXTENSION IF EXISTS pgcrypto;
