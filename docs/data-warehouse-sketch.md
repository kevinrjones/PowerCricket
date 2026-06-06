    # Data Warehouse Sketch (First Pass)

This is a conceptual starting point for analytics-oriented SQLite outputs. It is not final and should evolve from real query needs.

## Design intent

- Support fast, flexible analytical queries for exploratory UI
- Keep cricket vocabulary explicit
- Allow incremental addition of facts/dimensions
- Keep ETL and app loosely coupled via stable data contracts

## Candidate fact tables

1. `fact_batting_innings`
   - Grain: one batting innings by one player in one match innings
   - Measures: runs, balls_faced, fours, sixes, strike_rate, dismissal flags
2. `fact_bowling_innings`
   - Grain: one bowling spell/stat line by one player in one match innings
   - Measures: overs, maidens, runs_conceded, wickets, economy
3. `fact_fielding_dismissals`
   - Grain: one fielding dismissal involvement event
   - Measures: dismissal_count (typically 1), dismissal subtype indicators
4. `fact_partnerships`
   - Grain: one batting partnership in one innings
   - Measures: partnership_runs, balls_faced, wicket_number
5. `fact_match_results`
   - Grain: one team result row per match
   - Measures: win/loss/draw/no-result indicators, margin metrics
6. `fact_official_appointments`
   - Grain: one official appointment in one match
   - Measures: appointment_count (typically 1), role flags

## Candidate dimension tables

- `dim_player`
- `dim_team`
- `dim_match`
- `dim_date`
- `dim_ground`
- `dim_venue`
- `dim_tournament`
- `dim_match_type`
- `dim_innings`
- `dim_batting_position`
- `dim_dismissal_type`
- `dim_official`
- `dim_dataset`

## Key modeling concepts

### Surrogate keys

- Use integer warehouse keys (`player_key`, `match_key`, etc.) in fact tables.
- Benefits:
  - compact joins
  - stable joins even if source identifiers vary by dataset
  - easier handling of historical naming/value changes

### Natural/source keys

- Preserve source identifiers from MySQL (or source system) in dimensions as natural keys.
- Example: `source_player_id`, `source_match_id`.
- Keep unique constraints where possible per dataset.

### Slowly changing data (SCD)

- Candidate Type 2 handling where historical names/attributes matter (team names, venue naming, official naming).
- For initial slice, Type 1 may be acceptable to simplify delivery; document where history fidelity is required.

### Match type values

- `dim_match_type` should standardize canonical values such as:
  - `TEST`
  - `ODI`
  - `T20I`
  - (extendable for additional formats)

### Cross-database dataset identity

- `dim_dataset` and dataset metadata should provide globally stable dataset ids (for example `men_intl_v1`).
- Saved queries should reference dataset identity + compatibility version, not only display names.

### Why star-shaped helps

- Most user questions combine measures + multiple filters + grouping.
- Star-style facts plus reusable dimensions reduce query complexity and improve index/selectivity strategy.
- New questions can often be added by introducing measures or dimensions without redesigning all existing tables.

## Example question mapping

1. **Top run scorers in ODI cricket since 2010**
   - Fact: `fact_batting_innings`
   - Dimensions: `dim_player`, `dim_match_type`, `dim_date`, `dim_dataset`

2. **Best bowling figures by ground**
   - Fact: `fact_bowling_innings`
   - Dimensions: `dim_player`, `dim_ground` (or `dim_venue`), `dim_match_type`, `dim_dataset`

3. **Highest opening partnerships by opposition**
   - Fact: `fact_partnerships`
   - Dimensions: `dim_team`, `dim_innings`, `dim_match`, `dim_match_type`, `dim_dataset`

4. **Team record by tournament (wins/losses)**
   - Fact: `fact_match_results`
   - Dimensions: `dim_team`, `dim_tournament`, `dim_date`, `dim_match_type`, `dim_dataset`

5. **Official appointments by match type and year**
   - Fact: `fact_official_appointments`
   - Dimensions: `dim_official`, `dim_match_type`, `dim_date`, `dim_dataset`

## Initial SQLite prototype recommendation

- Implement only the smallest subset needed for first real question:
  - `fact_batting_innings`
  - `dim_player`, `dim_date`, `dim_match_type`, `dim_dataset`
- Add other facts/dimensions incrementally as each new question requires them.