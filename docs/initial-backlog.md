# Initial Incremental Backlog

Use small vertical slices. Each item is intended to be independently implementable while keeping the app runnable.

## BL-01 Create docs foundation

- **Goal**: Establish core planning documents for outside-in delivery.
- **User-visible outcome**: Team has shared direction and incremental plan.
- **Notes**: This is the current documentation sprint.
- **Suggested acceptance criteria**:
  - `docs/product-vision.md` exists
  - `docs/outside-in-development-plan.md` exists
  - `docs/architecture-notes.md` exists
  - `docs/data-warehouse-sketch.md` exists
  - `docs/query-ux-notes.md` exists
  - `docs/initial-backlog.md` exists
  - `docs/open-questions.md` exists

## BL-02 Confirm launch targets and baseline runs

- **Goal**: Verify desktop, ios, and Android launch paths.
- **User-visible outcome**: Known-good baseline before feature work.
- **Notes**: No product behavior change yet.
- **Suggested acceptance criteria**:
  - Desktop launch command/run config documented and works
  - Android launch command/run config documented and works
  - ios launch command/run config documented and works

## BL-03 Replace scaffold with Explore home screen

- **Goal**: Introduce first user-facing PowerCricket screen.
- **User-visible outcome**: App opens to an “Explore Cricket Statistics” home shell.
- **Notes**: Keep stateless composable where possible.
- **Suggested acceptance criteria**:
  - Shared `App()` renders a branded home/explore view
  - Screen contains entry point to query-building flow
  - Layout behaves reasonably on desktop/tablet widths

## BL-04 Add fake query templates

- **Goal**: Make discovery concrete with top-level templates.
- **User-visible outcome**: User can pick sample questions (runs, bowling, partnerships).
- **Notes**: Backed by fake data/state only.
- **Suggested acceptance criteria**:
  - Template list is visible and selectable
  - Selected template updates visible query summary
  - At least 3 templates are present

## BL-05 Add fake result table

- **Goal**: Show realistic results shape before real database integration.
- **User-visible outcome**: User sees sortable rows/cards from fake fixture.
- **Notes**: Keep result model close to planned domain terms.
- **Suggested acceptance criteria**:
  - Results section renders fake records
  - At least one sortable column works in UI state
  - Empty/loading/error placeholder states are represented

## BL-06 Introduce query-builder state object

- **Goal**: Centralize immutable state for query configuration.
- **User-visible outcome**: Filter/template changes are reflected consistently.
- **Notes**: Use explicit state + events; keep logic testable.
- **Suggested acceptance criteria**:
  - State model includes template, filters, sort, result mode
  - Event handlers update state deterministically
  - Unit tests cover key state transitions

## BL-07 Add active filter chips

- **Goal**: Make current constraints visible and easy to remove.
- **User-visible outcome**: Users see and clear active filters quickly.
- **Notes**: Important for discoverability and trust.
- **Suggested acceptance criteria**:
  - Active filters render as chips above results
  - Removing a chip updates query state and results
  - Chip list handles zero/one/many filters gracefully

## BL-08 Add saved-query placeholder panel

- **Goal**: Establish UX position for saved queries early.
- **User-visible outcome**: User can see save/load affordance (even if stubbed).
- **Notes**: Persistence can be fake/in-memory first.
- **Suggested acceptance criteria**:
  - Save action creates an in-session saved item
  - Saved item can be re-selected to restore visible state
  - UI indicates placeholder/prototype behavior clearly

## BL-09 Add sample SQLite fixture database

- **Goal**: Introduce first read-only SQLite artifact for analytics.
- **User-visible outcome**: None directly; enables real query execution.
- **Notes**: Keep fixture very small and versioned.
- **Suggested acceptance criteria**:
  - Fixture file included with deterministic test data
  - Schema includes minimal dims/fact for batting top-runs use case
  - Data-loading path is documented per platform

## BL-10 Implement first real read-only query

- **Goal**: Replace fake ranking data with real SQLite query output.
- **User-visible outcome**: Top-runs question returns real data with filters.
- **Notes**: Keep query scope narrow and testable.
- **Suggested acceptance criteria**:
  - Query supports match type + date range filters
  - Sort and limit behavior are correct
  - Integration/unit tests validate mapping and filtering

## BL-11 Add dataset registry concept

- **Goal**: Prepare for multiple databases without immediate full implementation.
- **User-visible outcome**: Dataset identity visible in query context.
- **Notes**: Start with single dataset registration.
- **Suggested acceptance criteria**:
  - Dataset metadata model exists (id, display name, version)
  - Query model references dataset id
  - UI can show active dataset

## BL-12 Add ETL design notes and script placeholder

- **Goal**: Make ETL a concrete parallel workstream.
- **User-visible outcome**: None directly; enables reliable data refresh pipeline.
- **Notes**: Keep ETL separate from client app runtime.
- **Suggested acceptance criteria**:
  - ETL readme/design note exists with source-to-target mapping
  - Placeholder script/app entry point exists
  - Validation checks are listed (row counts and spot checks)

## BL-13 Add first schema migration/export placeholder

- **Goal**: Prepare versioned evolution path for SQLite output.
- **User-visible outcome**: None directly.
- **Notes**: Prefer explicit schema version metadata.
- **Suggested acceptance criteria**:
  - Initial schema version metadata defined
  - Placeholder migration/export mechanism documented
  - Compatibility-check strategy documented for app startup

## Suggested first implementation step

- Start with **BL-03 Replace scaffold with Explore home screen** immediately after documentation sprint.
- Keep BL-04 and BL-05 in the same short sprint to maintain outside-in momentum.