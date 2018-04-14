var exec = require('cordova/exec')

/**
 * Activity を最前面に強制的に移動
 *
 * @param {Function} success
 * @param {Function} error
 */
exports.toFront = function (success, error) {
  exec(success, error, 'MoveTask', 'toFront', [])
}
