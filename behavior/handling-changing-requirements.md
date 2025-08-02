# Interview Question: Tell me about a time when requirements kept changing or were unclear. How did you adapt and maintain productivity?

## Answer:
When I joined the Walmart Advertising Platform team, I noticed that the itemset index page — where advertisers manage grouped products — had become difficult to maintain. Filtering logic was scattered, hardcoded, and inconsistent. Every time a new filter was needed, engineers manually added it with duplicated logic. There were no clear requirements or long-term ownership of the filtering system, which made development slow and error-prone.

Instead of continuing to patch filters one by one, I stepped back and designed a flexible, scalable solution. I defined a **small set of reusable filter types** — such as `string` inputs, `singleSelect`, `multiSelect`, and `defaultSelect` — and turned the entire filtering interface into a **configurable and declarative system**.

I also instruct the backend team so that filters were processed dynamically: if a filter is passed in the config, the backend handles it; if not, it’s simply ignored. This design allowed us to:
- Quickly add or remove filters without code changes
- Avoid code duplication
- Empower PMs to experiment without lengthy dev cycles

I also made the filtering module **portable**, so it could be reused in other item-related pages and even by other teams. This made the system more consistent and easier to extend across the platform.

- Most importantly, I **prioritized delivery in small, testable increments** — so even if one piece changed, the rest of the system remained stable and productive.


Despite the initial lack of clear scope, I took initiative to define the structure, align the team, and build something forward-compatible. It turned an unstable area of the product into a clean, reusable system, and significantly improved both developer velocity and UX consistency.

This approach helped the team stay flexible without sacrificing velocity or quality. We were able to hit our deadlines, even as the scope evolved, and the codebase remained clean and maintainable.
