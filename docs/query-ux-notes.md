# Query UX Notes (Exploration)

Purpose: design an exploratory query-building experience that works for cricket users without database expertise.

## Main interaction model

1. **Start with “What do you want to explore?”**
   - Players
   - Teams
   - Matches
   - Grounds
   - Partnerships
   - Officials

2. **Choose analysis type**
   - Ranking
   - Trend over time
   - Head-to-head
   - Distribution
   - Record search
   - Comparison

3. **Choose measures**
   - Runs
   - Average
   - Strike rate
   - Wickets
   - Economy
   - Catches
   - Matches

4. **Apply filters**
   - Match type
   - Date range
   - Opposition
   - Team
   - Ground
   - Tournament
   - Innings

5. **Choose result presentation**
   - Table
   - Cards
   - Chart
   - Timeline
   - Comparison view

## UI concepts

### Faceted filter sidebar

- Persistent filter panel on desktop/tablet
- Group filters by topic (competition, time, teams, context)
- Show active count per filter group

### Result table with sortable columns

- Table-first default for analytical clarity
- Sticky header and sort affordance per metric column
- Optional row expansion for additional stats

### Top-level question templates

- “Top run scorers”, “Best bowling figures”, “Head-to-head team record”
- Templates prefill subject, common measures, default sort

### Searchable selectors

- Searchable player/team/ground selectors for large lists
- Include recent selections for fast repeat workflows

### Sliders/date-range controls

- Date range picker with quick presets (all time, last 5 years, decade)
- Numeric slider only where it improves understanding (for example minimum matches)

### Chips for active filters

- Show all active filters as removable chips above results
- Clicking chip removes or edits the filter

### “Explain this query” panel

- Human-readable summary of current query logic
- Useful for trust, shareability, and saved-query clarity

### Saved query panel

- List of saved explorations
- Metadata shown: title, dataset, last opened, version compatibility status

### Responsive layout for desktop/tablet

- Wide: left filters, center results, right explain/saved panel (optional)
- Medium: collapsible side panels with persistent active-filter bar
- Keep controls discoverable without hiding core actions behind deep menus

## Example user flows

## Flow 1: Top run scorers in ODI cricket since 2010

- **Starting question**: “Show top run scorers”
- **UI selections**:
  - Explore: Players
  - Analysis: Ranking
  - Measure: Runs
- **Filters**:
  - Match type = ODI
  - Date range = 2010-01-01 to today
- **Result view**:
  - Table sorted by runs descending
  - Columns: Player, Matches, Innings, Runs, Average, Strike Rate
- **Possible refinements**:
  - Add min innings filter
  - Switch to cards for top 10 summary
  - Compare by decade groups

## Flow 2: Best bowling figures by ground

- **Starting question**: “Best bowling figures”
- **UI selections**:
  - Explore: Players
  - Analysis: Record search
  - Measure: Wickets (with runs conceded secondary)
- **Filters**:
  - Ground selector = user-selected ground or all grounds
  - Match type = Test/ODI/T20I toggle
- **Result view**:
  - Table with best figures first (for example 8/42)
  - Option to switch to card summaries per ground
- **Possible refinements**:
  - Add date range
  - Add team/opposition filters
  - Group by ground and compare top performances

## Flow 3: Highest opening partnerships by opposition

- **Starting question**: “Opening partnerships”
- **UI selections**:
  - Explore: Partnerships
  - Analysis: Ranking
  - Measure: Partnership runs
- **Filters**:
  - Wicket number = 1 (opening)
  - Opposition = selected team(s)
  - Match type and date range optional
- **Result view**:
  - Table with pair, opposition, runs, venue, date
  - Sort by partnership runs descending
- **Possible refinements**:
  - Limit to neutral venues
  - Add tournament filter
  - Convert to timeline view for trend over years

## Early UX principles for implementation slices

- Keep the first slice template-driven, not free-form SQL-like.
- Make active state visible at all times (filters, sorts, dataset).
- Prefer clear defaults over empty screens.
- Preserve UI state while users refine filters.
- Add charting only after table workflow is robust and understandable.