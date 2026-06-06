# Open Questions

This list captures unresolved decisions to drive upcoming spikes and implementation slices.

## Product

1. Which cricket datasets are first after men’s international (if any in wave 1)?
2. Which question templates are most important for first public/internal milestone?
3. What level of statistical detail is required initially (basic aggregates only vs advanced splits)?
4. Is ball-by-ball data required early, or is scorecard-level data enough for phase 1?
5. Should dataset switching be explicit first, with auto-selection added later?

## Data

1. What is the exact current MySQL schema and table/column inventory for source extraction?
2. Which source keys are stable and safe as long-term natural keys?
3. How are players/teams/grounds identified across multiple datasets today?
4. Are historical team names and venue names versioned in source data?
5. How should abandoned/no-result/DLS matches be represented for analytics consistency?
6. Are there known data quality gaps that must be corrected during ETL?

## Technical

1. Should app querying use Room, jOOQ, SQLDelight, raw SQLite, or hybrid approach?
2. How will SQLite files be packaged, downloaded, updated, or replaced per platform?
3. How will database version compatibility be checked at app startup?
4. What is the preferred iOS database file-access/loading strategy in this project?
5. How will saved queries survive schema evolution and dataset version changes?
6. Should data access remain in `shared` initially or move to a dedicated data module early?

## UX

1. Should users begin primarily from entities, question templates, or analysis types?
2. How advanced should the query builder be in MVP (template-first vs free-form controls)?
3. Do we need charting in early milestones, or should table-first remain the primary path?
4. How do we avoid overwhelming non-technical users while still supporting power filters?
5. How should we communicate result updates quickly (auto-run, apply button, progressive feedback)?
6. What saved-query affordances are essential for MVP (rename, tags, recents, sharing)?

## Process and delivery

1. Should ETL prototype be built in parallel with UI walking skeleton or one sprint later?
2. Which metrics define acceptable interactivity for exploratory querying on tablet/desktop hardware?
3. What evidence is required before committing to a long-term query engine choice?