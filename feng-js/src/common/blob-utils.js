/**
 * 将 Blob 对象转换为JSON 对象
 * @param {Blob} blob - 需要转换的 Blob 对象
 * @returns {Promise<any>} - 返回一个 Promise，解析为指定格式的数据
 */
function toJson(blob) {
  let reader = new FileReader();
  return new Promise((resolve, reject) => {
    reader.onload = function (e) {
      try {
        const jsonData = JSON.parse(e.target.result);
        resolve(jsonData);
      } catch (error) {
        reject(error);
      }
    };
    reader.onerror = function (error) {
      reject(error);
    };
    reader.readAsText(blob);
  });
}

export {toJson};
