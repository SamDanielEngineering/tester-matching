export async function getTesters(deviceIds, countries) {
    try {
        const response = await fetch('http://localhost:8080/api/v1/tester', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({"deviceIds": deviceIds, "countries":countries})
            });
        return await response.json();
    } catch (error) {
        return [];
    }
}
