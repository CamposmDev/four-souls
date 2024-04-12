/**
 * Verifies if the provided request body meets the required fields for the BaseCard class
 * @param {Request} req 
 * @param {Response} res 
 * @param {NextFunction} next 
 * @returns 
 */
function verifyBaseCard(req, res, next) {
    if (!req || !req.body) {
        return res.status(400).json({"message": "Missing body"});
    }
    const card = req.body;
    /* Ensure the BaseCard fields exist */
    if (!card.id) {
        return res.status(400).json({"message": "Missing \"id\" field"});
    }
    if (!card.image) {
        return res.status(400).json({"message": "Missing \"image\" field"})
    }
    if (!card.cardType) {
        return res.status(400).json({"message": "Missing \"cardType\" field"});
    }
    if (!card.cardSet) {
        return res.status(400).json({"message": "Missing \"cardSet\" field"});
    }
    next();
}

/**
 * Verifies if the provided request body meets the required fields for a BaseCard class and its subclasses.
 * The required fields depend on the specific subclass provided.
 * @param {Request} req 
 * @param {Response} res 
 * @param {NextFunction} next 
 */
function verify(req, res, next) {
    verifyBaseCard(req, res, next);
}

module.exports = {
    verify,
    verifyBaseCard
}