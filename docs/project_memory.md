# Project Memory

## 2026-06-05 14:19 — Initial outside-in documentation set

- **Title**: Initial PowerCricket product and architecture documentation sprint
- **What was shipped**:
  - Created initial docs set for product direction, outside-in phased plan, architecture notes, warehouse sketch, query UX notes, incremental backlog, and grouped open questions.
  - Anchored documentation to actual current project structure (`androidApp`, `desktopApp`, `shared`, `domain`) and existing dependencies.
- **Key decisions**:
  - Use outside-in incremental delivery with a walking skeleton before deep data-layer commitments.
  - Keep query engine and SQLite access approach intentionally open pending prototype evidence.
  - Treat ETL (MySQL -> analytical SQLite) as a separate concern from app runtime.
- **Gotchas**:
  - Existing codebase still contains legacy scorer-oriented package naming in domain areas.
  - Shared UI is currently scaffold-level and needs a first real Explore screen slice.
  - No prior docs/task memory convention existed in-repo; `docs/` was introduced for planning artifacts.
- **Test coverage areas**:
  - Documentation-only sprint; no code changes and no automated tests executed.