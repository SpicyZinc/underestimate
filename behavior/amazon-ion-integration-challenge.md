# Interview Question: Describe a situation when you failed and how you managed it

## Question:
**Describe a situation when you failed and how you managed the situation.**

## Answer:
While working at Amazon, I was assigned a task to upgrade an API that returned product (ASIN) attributes. Initially, I assumed it would involve minor changes — like adding or renaming fields in the response. Our system architecture was React frontend, Express middleware, and Java backend.

During integration, I discovered that the API response format had changed from JSON to **Amazon Ion**, a superset of JSON used internally at Amazon. Since I was mainly focused on frontend work at the time, I wasn’t familiar with Ion or how to process it in Java.

Once I identified the issue, I immediately shifted focus. I researched Ion, learned how to use the `ion-java` library, and updated the backend code to correctly parse and convert the Ion response. I also coordinated with the backend team to confirm assumptions and ensure compatibility across systems.

Despite the surprise format change and lack of early communication, I took full ownership, quickly ramped up on unfamiliar tech, and delivered the update on time. It reinforced the importance of validating assumptions and showed my ability to adapt and deliver in cross-functional environments.


---

## Why Amazon switched from JSON to Ion:

Amazon adopted **Ion** over JSON in some services because it supports **richer data types**, **annotations for metadata**, and an **optional binary format** that improves performance. It's also more suitable for complex systems like product catalogs, where attributes may include timestamps, versioning, or nested metadata. Ion helps ensure **data consistency, efficient storage, and schema evolution** — all important at Amazon's scale.