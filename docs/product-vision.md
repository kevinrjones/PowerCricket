# PowerCricket Product Vision

## What PowerCricket is

PowerCricket is a Kotlin Multiplatform application for exploring cricket statistics quickly, without requiring users to understand database schemas or SQL.

The product focus is discovery: users should be able to start with a broad cricket question, refine it interactively, and reach useful answers fast.

## Who it is for

- Cricket enthusiasts
- Analysts, scorers, writers, and fans who want to investigate records and trends
- Users who know cricket language but are not database experts

## Target platforms

Initial product target:

- Desktop (primary first-class experience)
- Android tablets in landscape mode
- iPad/tablets in landscape mode

Near-term emphasis is desktop/tablet-sized layouts, not phone-first UX.

## Initial scope

- Men’s international cricket
- Local, read-only analytics from SQLite database(s)
- Exploratory query building for rankings, comparisons, trends, and filtered records

## Future scope

- Additional datasets (women’s professional, domestic, curated historical datasets)
- Multi-database operation with either:
  - automatic dataset selection from query context, or
  - explicit user dataset switching
- Broader analysis categories (bowling, fielding, partnerships, officials, tournaments, venue-driven analysis)

## What “discovering cricket statistics” means

In PowerCricket, discovery means users can:

1. Start from a cricket question template or analysis type
2. Choose metrics to display
3. Apply cricket-specific filters
4. Group, sort, and narrow results interactively
5. Save and reload views/queries, including UI state

Discovery is successful when users can move from broad curiosity to concrete answers through guided UI controls rather than technical query syntax.

## Key product principles

1. **Fast exploration**
   - Query interactions should feel responsive enough for iterative refinement.
2. **Flexible filtering**
   - Support combinations of match type, date range, opposition, venue, innings, and result context.
3. **Cricket-friendly vocabulary**
   - UI language should use cricket terms directly (runs, wickets, innings, opposition, ground, etc.).
4. **Saved queries**
   - Users can save, reload, and continue previous explorations.
5. **Local-first operation**
   - Core analytics run against local SQLite; no initial dependency on remote services.
6. **Multiple datasets/databases**
   - Architecture should support one or many local databases without redesigning the UI/query model.

## Current assumptions (initial)

- App data is read-only for cricket statistics.
- Existing normalized MySQL source is transformed externally into analytics-oriented SQLite.
- Schema/query engine choices remain intentionally open until prototype evidence is gathered.