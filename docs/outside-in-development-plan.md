# Outside-In Development Plan (Initial)

This plan is intentionally incremental and reversible. The goal is to keep PowerCricket runnable while moving from visible user flows toward real analytical behavior.

## Current baseline (from project inspection)

- Modules: `androidApp`, `desktopApp`, `shared`, `domain`, `iosapp`
- Entry points:
  - Desktop: `desktopApp/src/main/kotlin/com/knowledgespike/powercricket/main.kt`
  - Android: `androidApp/src/main/kotlin/com/knowledgespike/powercricket/MainActivity.kt`
  - iosApp: `iosApp/iosApp/iOSApp.swift`
  - Shared UI root: `shared/src/commonMain/kotlin/com/knowledgespike/powercricket/App.kt`
- Existing dependencies already include Compose Multiplatform, Navigation 3 (`jetbrains-compose-navigation`), Koin, Arrow, SQLite bundled, and Room runtime/compiler.
- Current shared/domain code is mostly scaffold + legacy scorer-focused domain packages, so this work should proceed with small, explicit slices.

## Phase 0: Project understanding

### Goal

Create a reliable map of current structure and seams before introducing new behavior.

### Activities

- Inspect existing modules and source set layout
- Identify app entry points and initial launch paths
- Identify current shared UI/domain/data boundaries
- Identify dependency posture for Compose, SQLite, Room, Koin, navigation, serialization
- Record assumptions and unknowns; avoid major architecture changes

### Exit criteria

- Architecture notes and open questions documented
- Clear first walking-skeleton slice selected

## Phase 1: Walking skeleton

### Goal

A runnable, visible shell of the future product on desktop, ios, and Android tablet-like layouts (without blocking iOS compatibility).

### Scope

- Launch desktop app, ios App, and Android app successfully
- Keep iOS compile compatibility as a guardrail
- Replace placeholder click-demo with:
  - Home/Explore screen
  - Placeholder query-builder panel
  - Placeholder results panel
- Use fake in-memory data and stubbed query execution
- Demonstrate adaptive layout for large screens/tablets using `WindowWidthSizeClass`

### Exit criteria

- User can open app and navigate an exploratory shell
- Visible query controls and results placeholder are present
- App remains runnable across primary targets

## Phase 2: First real cricket question

### Candidate first question

`Top batters by runs, filterable by match type and date range`

### Required UI controls

- Question template selector (or fixed template screen)
- Measure selector (initially fixed to runs for MVP)
- Match type filter (Test/ODI/T20)
- Date range filter
- Sort control (runs descending default)
- Result limit control (for example top 10/25/50)

### Data and model scope

- Minimal query model for one entity (`player`) and one measure (`runs`)
- Fake fixture first, then replace with tiny SQLite fixture

### Result presentation

- Table with columns such as `Player`, `Matches`, `Innings`, `Runs`, `Average`
- Sort indicator and active-filter chips visible

### Exit criteria

- End-to-end flow answers one real question using read-only data source
- Filters and sorting demonstrably affect results

## Phase 3: Query model

### Goal

Define a compact internal model reusable across many cricket questions.

### Model elements

- `Dataset` (men_intl, women_pro, domestic, etc.)
- `Subject` / entity (`player`, `team`, `match`, `ground`, `official`)
- `Measures` (`runs`, `wickets`, `catches`, etc.)
- `Dimensions` (`year`, `opposition`, `ground`, `match_type`, etc.)
- `Filters` (typed operators and values)
- `Sorts`
- `Limit`
- Saved query metadata (name, created date, app/query version)

### Exit criteria

- Query model can represent the first real question and at least two near-term extensions

## Phase 4: SQLite analytical prototype

### Goal

Validate that a read-only SQLite analytical shape supports interactive query UX.

### Scope

- Define small star-schema-inspired prototype
- Load tiny seed dataset (enough for realistic filter/sort behavior)
- Add one to two analytical SQL queries (parameterized)
- Map query model -> SQL builder/executor adapter
- Keep data access replaceable as schema evolves

### Exit criteria

- First real question executes from SQLite fixture through app UI
- SQL and model mapping are documented and testable

## Phase 5: ETL planning

### Goal

Define a separate transformation flow from normalized MySQL source to SQLite analytical output.

### Scope

- Source model inventory (current MySQL normalized schema)
- Target analytical model mapping (facts/dimensions)
- Nightly ETL script/app process (separate from client app)
- Validation checks (row counts, referential integrity checks, metric spot checks)
- Versioned SQLite output files and metadata
- App-side database discovery/loading strategy

### Exit criteria

- ETL design notes and initial script/application placeholder exist
- Data contract between app and ETL output is explicit

## Phase 6: Saved queries

### Goal

Allow users to save and restore query definitions and UI state reliably over time.

### Scope

- Query definition serialization format
- UI state restoration (filters, selected columns/view mode, sorting)
- Saved query format versioning
- Missing database/schema-change handling strategy

### Exit criteria

- User can save and reload at least one query end-to-end in walking skeleton
- Backward-compatibility policy is documented

## Phase 7: Expansion

### Goal

Scale the model and UI incrementally to additional analysis categories and datasets.

### Expansion areas

- Bowling
- Fielding
- Partnerships
- Officials
- Venues
- Tournaments
- Team-vs-team analysis
- Multi-database support and cross-dataset UX

### Exit criteria

- Each expansion delivered as small vertical slices with tests and clear acceptance criteria

## Implementation cadence recommendation

For each slice:

1. Add user-visible UI behavior first
2. Add or evolve query state model
3. Connect fake adapter, then real SQLite adapter
4. Add tests for state transitions and query mapping
5. Keep app runnable and reversible
