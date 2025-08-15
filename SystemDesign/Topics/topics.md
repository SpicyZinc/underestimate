# System Design Topics

# System Design Topics

## 📌 Index

1. [Design Bit.ly](#1-design-bitly)
2. [Design Dropbox](#2-design-dropbox)
3. [Design a Local Delivery Service](#3-design-a-local-delivery-service)
4. [Design a News Aggregator](#4-design-a-news-aggregator)
5. [Design Ticketmaster](#5-design-ticketmaster)
6. [Design FB News Feed](#6-design-fb-news-feed)
7. [Design Instagram – tested 2025 Meta](#7-design-instagram--tested-2025-meta)
8. [Design FB Live Comments – tested 2025 Meta](#8-design-fb-live-comments--tested-2025-meta)
9. [Design FB Post Search](#9-design-fb-post-search)
10. [Design Tinder](#10-design-tinder)
11. [Design LeetCode – tested 2024 Meta](#11-design-leetcode--tested-2024-meta)
12. [Design WhatsApp](#12-design-whatsapp)
13. [Design Yelp](#13-design-yelp)
14. [Design Strava](#14-design-strava)
15. [Design a Rate Limiter](#15-design-a-rate-limiter)
16. [Design Online Auction](#16-design-online-auction)
17. [Design a Price Tracking Service](#17-design-a-price-tracking-service)
18. [Design Uber](#18-design-uber)
19. [Design Robinhood](#19-design-robinhood)
20. [Design Google Docs](#20-design-google-docs)
21. [Design a Distributed Cache](#21-design-a-distributed-cache)
22. [Design YouTube](#22-design-youtube)
23. [Design YouTube Top K](#23-design-youtube-top-k)
24. [Design a Job Scheduler](#24-design-a-job-scheduler)
25. [Design Web Crawler](#25-design-web-crawler)
26. [Design Ad Click Aggregator](#26-design-ad-click-aggregator)
27. [Design a Payment System](#27-design-a-payment-system)
---

## Design Bit.ly
- Functional:
  - Shorten long URLs
  - Redirect short links to original URL
- Non-Functional:
  - High availability
  - Low latency redirects

## Design Dropbox
- Functional:
  - File upload/download
  - Sync across devices
- Non-Functional:
  - Data durability
  - Scalability to millions of users

## Design a Local Delivery Service
- Functional:
  - Track orders in real time
  - Match drivers to customers
- Non-Functional:
  - Reliability in real-time updates
  - Fault tolerance

## Design a News Aggregator
- Functional:
  - Fetch news from multiple sources
  - Allow search and filter by category
- Non-Functional:
  - Crawl efficiency
  - Scalability to large number of feeds

## Design Ticketmaster
- Functional:
  - Purchase/reserve tickets
  - Handle seat availability in real time
- Non-Functional:
  - Handle high traffic spikes
  - Prevent double booking

## Design FB News Feed
- Functional:
  - Display personalized posts
  - Like/comment/share
- Non-Functional:
  - Low latency
  - Feed ranking algorithms

## Design Instagram – tested 2025 Meta
- Functional:
  - Photo/video upload
  - Follow/unfollow users
- Non-Functional:
  - Media storage scalability
  - Fast content delivery

## Design FB Live Comments – tested 2025 Meta
- Functional:
  - Real-time comment updates
- Non-Functional:
  - Low-latency pub/sub
  - Consistency under heavy load

## Design FB Post Search
- Functional:
  - Search posts by keyword
  - Sort by recency or like count
- Non-Functional:
  - Fast search (<500ms)
  - Make new posts searchable within 1 min

## Design Tinder
- Functional:
  - Swipe left/right
  - Show mutual matches
- Non-Functional:
  - Real-time matching
  - Handle high user concurrency

## Design LeetCode – tested 2024 Meta
- Functional:
  - Code editor + test cases
  - Submission + leaderboard
- Non-Functional:
  - Isolated code execution
  - Low-latency feedback

## Design WhatsApp
- Functional:
  - Real-time chat
  - Group messaging
- Non-Functional:
  - Guaranteed delivery (or retry)
  - Scalable to billions of users

## Design Yelp
- Functional:
  - Browse/search businesses
  - Leave reviews
- Non-Functional:
  - Location-based search
  - Efficient indexing

## Design Strava
- Functional:
  - Track fitness activity via GPS
  - Follow athletes
- Non-Functional:
  - Real-time data ingestion
  - Durable activity storage

## Design a Rate Limiter
- Functional:
  - Enforce request limits per user
- Non-Functional:
  - Fast in-memory check
  - Accuracy in distributed systems

## Design Online Auction
- Functional:
  - Place and track bids
  - Notify winners
- Non-Functional:
  - Prevent overbidding
  - Handle concurrency

## Design a Price Tracking Service
- Functional:
  - Periodically crawl product prices
  - Notify users of changes
- Non-Functional:
  - Efficient scheduling
  - Avoid site bans (robots.txt handling)

## Design Uber
- Functional:
  - Match drivers and riders
  - Track ETA/location
- Non-Functional:
  - Real-time routing
  - Scalability across regions

## Design Robinhood
- Functional:
  - Place trades
  - Show stock trends
- Non-Functional:
  - Low-latency order handling
  - Real-time updates

## Design Google Docs
- Functional:
  - Real-time collaborative editing
- Non-Functional:
  - Conflict-free merging
  - Low latency sync

## Design a Distributed Cache
- Functional:
  - Store frequent reads in-memory
- Non-Functional:
  - High hit rate
  - Replication for failover

## Design YouTube
- Functional:
  - Upload and stream videos
- Non-Functional:
  - CDN usage
  - Scalable encoding and storage

## Design YouTube Top K
- Functional:
  - Track most viewed videos
  - Time window support (1h, 1d, etc.)
- Non-Functiona
