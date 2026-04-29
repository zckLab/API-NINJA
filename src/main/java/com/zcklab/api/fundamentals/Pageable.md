# 📄 Pagination with Pageable in Spring Boot

## 🧠 Objective

Implement pagination in the API to avoid loading all data at once, improving:

* Performance
* Scalability
* Response organization

---

## ❌ Before (without pagination)

```java
public List<NinjaResponseDTO> findAllNinjas() {
    List<Ninja> ninjas = repositoryNinja.findAll();

    if (ninjas.isEmpty()) {
        throw new NinjaNotFoundException("Ninja not found");
    }

    return ninjas.stream()
            .map(ninjaMapper::toResponseNinjaDTO)
            .collect(Collectors.toList());
}
```

### ⚠️ Problems

* Returns **all data from the database**
* May cause:

    * High memory consumption
    * Slowness
    * Application crashes
* Not scalable

---

## ✅ After (with pagination)

```java
public Page<NinjaResponseDTO> findAllNinjas(int page, int items) {

    Pageable pageable = PageRequest.of(page, items);

    Page<Ninja> ninjasPage = repositoryNinja.findAll(pageable);

    return ninjasPage.map(ninjaMapper::toResponseNinjaDTO);
}
```

---

## 🔍 What changed?

### 1. Using `Pageable`

```java
Pageable pageable = PageRequest.of(page, items);
```

#### 📌 What does it do?

Creates a pagination configuration with:

* `page` → page number (starts at 0)
* `items` → number of items per page

#### 🧠 Internally becomes:

```sql
LIMIT items OFFSET page * items
```

#### 📦 Example:

```java
PageRequest.of(2, 10);
```

Equivalent to:

```sql
LIMIT 10 OFFSET 20
```

---

### 2. Using `Page<T>` instead of `List<T>`

```java
Page<Ninja> ninjasPage
```

#### 📌 Difference:

| Type   | What it returns               |
| ------ | ----------------------------- |
| `List` | Only the data                 |
| `Page` | Data + pagination information |

#### 📊 `Page` contains:

* content (`getContent()`)
* total elements
* total pages
* current page
* whether it is the last page

---

### 3. Removing `stream()`

Before:

```java
ninjas.stream().map(...).collect(...)
```

After:

```java
ninjasPage.map(ninjaMapper::toResponseNinjaDTO);
```

#### ✔️ Reason

`Page` already provides a built-in `.map()` method.

---

### 4. Removing the Exception

Before:

```java
if (ninjas.isEmpty()) {
    throw new NinjaNotFoundException("Ninja not found");
}
```

After: ❌ removed

#### ⚠️ Reason

In REST APIs:

* An empty list **is not an error**
* It is a valid response

#### 📦 Correct example:

```json
{
  "content": [],
  "totalElements": 0,
  "totalPages": 0
}
```

---

## 🌐 Controller

```java
@GetMapping
public Page<NinjaResponseDTO> getAllNinjas(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int items
) {
    return serviceNinja.findAllNinjas(page, items);
}
```

---

## 🔍 Explanation

### `@RequestParam`

Defines URL parameters:

```
/ninjas?page=0&items=10
```

### Default values:

```java
defaultValue = "0"
defaultValue = "10"
```

Used when parameters are not provided in the request.

---

## ❓ Why not use `ResponseEntity`?

### ✔️ You can use it:

```java
public ResponseEntity<Page<NinjaResponseDTO>> getAllNinjas(...) {
    return ResponseEntity.ok(serviceNinja.findAllNinjas(page, items));
}
```

### ✔️ But it is not mandatory

Spring already:

* Returns HTTP 200 automatically
* Serializes the response to JSON

---

## 🧠 Important concept

> `Pageable` does not contain data, it contains **query instructions**

It defines:

* Page
* Size
* Sorting (optional)

---

## 🚀 Benefits of Pagination

* Reduces database load
* Avoids memory overload
* Improves response time
* Enables navigation through large datasets

---

## 🧩 Conclusion

Pagination with `Pageable`:

* Shifts responsibility to the database
* Makes the API more efficient
* Follows industry standards

---

## 🔽 Add Sorting with `Sort`

`Sort` defines the order in which data is returned from the database.
Without it, the database does **not guarantee any ordering**.

---

### ✅ Example (current implementation)

```java
PageRequest.of(
    page,
    items,
    Sort.by("name").ascending()
        .and(Sort.by("email").ascending())
);
```

#### 📌 Explanation

* First, data is sorted by `name` in ascending order (A → Z)
* If multiple records share the same `name`, a secondary sort is applied using `email`
* Since `email` is unique, it guarantees a **fully deterministic order**
* No additional tie-breakers are required

---

### 🚀 Professional Example (dynamic sorting)

Instead of hardcoding sorting logic, a more flexible approach is to let the client define it:

#### Controller

```java
@GetMapping
public ResponseEntity<Page<NinjaResponseDTO>> getAllNinjas(Pageable pageable) {
    return ResponseEntity.ok(serviceNinja.findAllNinjas(pageable));
}
```

#### Service

```java
public Page<NinjaResponseDTO> findAllNinjas(Pageable pageable) {
    return repositoryNinja.findAll(pageable)
            .map(ninjaMapper::toResponseNinjaDTO);
}
```

#### Request Example

```
/api/v1/users?page=0&size=10&sort=name,asc&sort=email,asc
```

---

### 🧠 Key Concept

> Using a unique field (such as email) as a secondary sort key ensures deterministic ordering when the primary field contains duplicate values.

---

### 🔧 Additional Improvements

* Use `Pageable` directly in the controller for more flexibility
* Implement filtering with `Specification` for advanced queries

---
