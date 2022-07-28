export async function getAllCountries() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/tester/countries');
        return await response.json();
    } catch (error) {
        return [];
    }
}
