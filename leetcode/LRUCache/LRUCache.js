/**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
    this.cache = new Map();
    this.capacity = capacity;
};

/** 
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
    if (!this.cache.has(key)) return -1;

    let val = this.cache.get(key);
    // Refresh key, since Map keeps insertion order,
    this.cache.delete(key);
    this.cache.set(key, val);

    return val;
};

/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
    // Delete key to refresh it
    this.cache.delete(key);
    if (this.cache.size === this.capacity) {
        this.cache.delete(this.cache.keys().next().value);
    }

    this.cache.set(key, value);
};


LRUCache.prototype.pugetLeastRecentt = function() {
    return Array.from(this.cache)[0];
};

LRUCache.prototype.getMostRecent = function() {
    return Array.from(this.cache)[this.cache.size - 1];
};

/** 
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */