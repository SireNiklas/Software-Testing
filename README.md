# CS 320 - Software Testing

Nicholas Harris

My testing work from CS 320. I wrote a bunch of JUnit tests for three back-end services and learned how to actually test software properly.

---

## What I Built

Three services with full test coverage:

**Contact Service** - manages contacts (add/update/delete)
- 59 tests, all passed

**Task Service** - manages tasks with CRUD operations  
- 40 tests, all passed

**Appointment Service** - manages appointments with date validation
- 14 tests, all passed

Total: 113 tests, 100% pass rate

---

## How I Make Sure Code Works and is Secure

**Test everything that can break:** I wrote tests for every validation - null checks, length limits, format requirements. If it can fail, there's a test for it.

**Boundary testing:** Most bugs happen at edges. For phone numbers, I tested 9 digits (too short), 10 digits (valid), and 11 digits (too long). Same thing with character limits.

**Actually test the errors:** Used `assertThrows()` to make sure invalid inputs throw exceptions like they should. Can't just test the happy path.

**Different test types:**
- Valid inputs (make sure they work)
- Invalid inputs (make sure they get rejected)
- Edge cases (empty strings, exact max lengths)
- Full workflows (add, update, verify, delete)

**Security through validation:** Every field gets validated. Phone numbers use regex to ensure exactly 10 digits - no letters, symbols, or anything else. Addresses are capped at 30 chars.

**IDs can't change:** Made IDs final so they can't be modified after creation. Prevents bugs from ID tampering.

---

## How I Figure Out What Users Need

**Read requirements carefully:** Before writing code, I actually read what it's supposed to do. Contact Service needed "exactly 10 digits" for phones, so that's what the validation does.

**Every requirement gets tested:** If requirements say "max 10 characters," I test with 10 (should work) and 11 (should fail).

**Think about real usage:** Users do weird stuff. They leave things empty, max out character limits, paste in garbage. Had to test all of it.

**Clear error messages:** When something fails, the exception says why. "Contact ID cannot be null and must be 10 characters or less" tells you exactly what's wrong.

---

## How I Design Software

**Figure out the data first:** What needs to be stored? What are the rules? Contact needs ID (10 chars max), name (10 chars), phone (exactly 10 digits), etc.

**Don't let things change if they shouldn't:** IDs are `final` because they shouldn't be updatable. Simpler and prevents bugs.

**Validate everything immediately:** Every constructor and setter checks input. Better to fail fast with a clear message than store bad data.

**Pick the right data structure:** Used HashMap for O(1) lookups instead of ArrayList (O(n)). When searching by ID constantly, HashMap is way faster.

**Keep it simple:** The services just do add, delete, update. No unnecessary features, just solid implementations.

**Test while building:** Didn't write everything then test. Built a class, tested it thoroughly, moved on. Way easier to fix bugs immediately.

---

## Testing Stuff I Used

**Unit testing** - each test focuses on one thing, runs fast  
**Boundary testing** - testing at edges where bugs hide  
**Exception testing** - making sure errors throw when they should  
**Integration testing** - testing complete workflows, not just individual methods

---

## What I Actually Learned

**Testing is a totally different mindset.** Coding = making things work. Testing = trying to break things. You have to actively look for problems in your own code.

**Most bugs are at boundaries.** The validation bugs I found were all at edges - exactly 10 characters, exactly 10 digits, empty strings.

**Tests catch your blind spots.** I forgot to check if phone numbers were actually numeric (not just 10 characters). My test caught it because I was thinking like a user, not like the person who wrote the code.

**Writing tests takes time but saves way more time later.** 113 tests took hours but that's nothing compared to fixing production bugs.

**Helper methods are clutch.** My `getFutureDate()` helper gets used 30+ times. Made writing tests way faster.

---

## Tech

Java • JUnit 5 • HashMap • Input Validation

---

*CS 320 - SNHU*
