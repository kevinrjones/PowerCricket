# Architecture Notes (Initial)

These notes define a starting architecture direction for incremental delivery. They are intentionally provisional and should be refined through working slices.

## Current implementation snapshot

- KMP modules present: `shared`, `domain`, `desktopApp`, `androidApp`, `iosApp`
- UI entry points call shared `App()`
- Shared UI currently a scaffold demo (`App.kt`)
- Existing dependencies already include Compose, Navigation 3, Koin, Arrow, SQLite bundled, Room runtime/compiler
- Domain module contains legacy scorer-oriented package structure; this should be treated as existing context rather than a final target architecture

## Proposed boundaries (initial)

### 1) UI layer

Responsibility:

- Screens, adaptive layouts, controls, and result presentation
- Emits user intents/events
- Renders explicit immutable UI state

Likely location:

- `shared/src/commonMain/.../ui/...`

### 2) Query-builder state layer

Responsibility:

- Holds current query draft state (selected template, measures, filters, sorts, view mode)
- Applies pure state transitions from UI events
- Produces query model requests for execution

Likely location:

- `shared/src/commonMain/.../feature/explore/state/...`

### 3) Domain/query model layer

Responsibility:

- Typed model for dataset/entity/measures/dimensions/filters/sorts/limits
- Explicit business failures (for invalid filter combos, unsupported dimensions, etc.)

Likely location:

- `domain/src/commonMain/...`

### 4) Data access layer

Responsibility:

- Translate query model to SQL
- Execute read-only queries against SQLite
- Map rows into domain/UI result models

Likely location:

- `shared` or dedicated data module (to be decided after prototype)

### 5) Dataset/database registry

Responsibility:

- Discover available SQLite datasets
- Provide metadata (dataset id, version, display name, compatibility)
- Select dataset explicitly or via lightweight selection rules

### 6) Platform-specific database file access

Responsibility:

- Resolve database file paths per platform (desktop/android/iOS)
- Handle read-only opening and lifecycle

### 7) Saved query/preferences storage

Responsibility:

- Persist serialized query definitions and UI state
- Version saved-query format and migration behavior

### 8) ETL tool/script (separate concern)

Responsibility:

- Transform normalized MySQL source into analytical SQLite outputs nightly
- Validate and version outputs
- Remains outside runtime client application

## Architectural uncertainties (intentionally unresolved)

- Whether data access should stay in `shared` initially or move to a dedicated `data` module early
- Whether existing `domain` module should be incrementally adapted or partially replaced for analytical-query concerns
- Final shape of route organization and feature packages in shared UI

## Trade-offs to evaluate with prototypes

## Room vs direct SQLite access

- **Room advantages**
  - Familiar API, schema tooling, compile-time query validation for fixed queries
  - Existing dependency already present
- **Room risks for this product**
  - Dynamic analytical queries can be awkward
  - May encourage entity/DAO patterns less aligned with exploratory analytics
- **Direct SQLite advantages**
  - Maximum flexibility for generated analytical SQL
  - Clear read-only control
- **Direct SQLite risks**
  - More manual SQL mapping and validation burden

## jOOQ vs hand-written SQL/query builder

- **jOOQ advantages**
  - Type-safe SQL DSL and composability for complex queries
- **jOOQ risks**
  - Additional setup complexity in KMP context and code generation workflow
- **Hand-written SQL advantages**
  - Simple startup path, full transparency of produced SQL
- **Hand-written SQL risks**
  - Higher risk of query duplication and maintenance drift without discipline

## Shared Compose UI vs platform-specific wrappers

- **Shared-first UI advantages**
  - Faster cross-platform feature delivery
  - Consistent query-building behavior
- **Shared-first risks**
  - Some platform-specific UX polish may require wrappers
- **Platform wrappers strategy**
  - Keep core exploration UI shared; allow thin host wrappers only where required

## Fake data first vs database-first

- **Fake-data-first advantages**
  - Fastest outside-in iteration for UX and state model
  - Lower early coupling to unstable schema
- **Fake-data-first risks**
  - UX assumptions might diverge from real query characteristics
- **Recommendation**
  - Start fake-first but introduce SQLite fixture early (Phase 2/4)

## One database per dataset vs one combined database

- **One-per-dataset advantages**
  - Clear ownership/versioning and smaller files
  - Easier replacement/update lifecycle
- **One-per-dataset risks**
  - Cross-dataset comparisons require extra logic
- **Combined database advantages**
  - Simpler single connection and global querying
- **Combined risks**
  - Larger file size, more complex update/version coupling

## Star schema vs fully denormalized tables

- **Star-shape advantages**
  - Balanced flexibility and performance for analytical filters/grouping
  - Reusable dimensions across facts
- **Fully denormalized advantages**
  - Very simple query path for specific reports
- **Recommendation**
  - Start star-inspired, allow selective derived/aggregate tables where proven useful

## Precomputed aggregate tables vs query-time aggregation

- **Precomputed aggregates advantages**
  - Faster response for common heavy queries
- **Precomputed risks**
  - More ETL complexity and versioning overhead
- **Query-time aggregation advantages**
  - Maximum flexibility for new questions
- **Recommendation**
  - Prefer query-time first; add targeted aggregates only when measured performance requires

## Decision policy

- Make decisions by vertical-slice evidence, not up-front preference.
- Record selected options and rationale as architecture decisions when they become stable.