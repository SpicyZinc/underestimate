# Interview Question: Tell me about a conflict you had at work

## Question:
**Tell me about a conflict you had at work.**

## Answer:

In Q1, the other team didn’t support filtering on their API, so we handled it on our side. In Q2, they started supporting filtering, and we aligned on the filter keys and expected values. Based on that agreement, we proceeded with integration using their existing API.

However, during integration, we discovered they had upgraded to a new API version and also introduced a new required parameter — `tenantId` — neither of which had been communicated to us. This change meant significantly more work on our side and risked us missing our delivery deadline.

I reached out to their team, explained the situation and the potential delay. We had a productive discussion and agreed on a solution: they would add the agreed-upon filters to the original API version, without requiring the new parameters. On our end, we used our own cached data and Kafka to keep it up to date.

This allowed us to stay on schedule while preserving the collaboration and avoiding rework.
