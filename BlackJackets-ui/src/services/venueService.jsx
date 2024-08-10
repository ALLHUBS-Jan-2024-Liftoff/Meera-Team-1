import axios from "axios";

const BASEAPIURL = "http://localhost:8090/venue";

export const fetchVenues = async () => {
    try {
        const response = await axios.get(`${BASEAPIURL}`);
        return response.data;
    } catch (error) {
        alert("Error occurs while fetching venues!", error);
        throw error;
    }
};

export const addVenue = async (venue) => {
    try {
        const response = await axios.post(`${BASEAPIURL}/add`,venue);
        return response.data;
    } catch (error) {
        alert("Error occurs while creating venue!", error);
        throw error;
    }
};

export const getVenueById = async (venueId) => await axios.get(`${BASEAPIURL}/${venueId}`);

// export const getVenueById = async (venueId) => {
//     try {
//         const response = await axios.get(`${BASEAPIURL}/${venueId}`);
//         console.log("Response",response.data);
//         return response.data;
//     } catch (error) {
//         alert("Error occurs while fetching a venue!", error);
//         throw error;
//     }
// };

export const editVenueById = async (venueId,venue) => {
    try {
        const response = await axios.put(`${BASEAPIURL}/${venueId}`,venue);
        return response.data;
    } catch (error) {
        alert("Error occurs while updating a venue!", error);
        throw error;
    }
};