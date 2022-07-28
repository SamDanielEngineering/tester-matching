export async function getAllDevices() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/device');
        return await response.json();
    } catch (error) {
        return [];
    }

}