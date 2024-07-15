import Foundation

private func logSite(_ data: [String: String]?) {
    guard let data = data else {
        print("No data to log")
        return
    }

    let url = URL(string: "https://yourapi.com/api/log")!
    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
    
    do {
        let jsonData = try JSONSerialization.data(withJSONObject: data, options: [])
        request.httpBody = jsonData
        
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let error = error {
                print("Error logging data: \(error)")
                return
            }
            
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode == 200 {
                    print("Data logged successfully")
                } else {
                    print("Failed to log data with status code: \(httpResponse.statusCode)")
                }
            }
        }
        
        task.resume()
    } catch {
        print("Error serializing data to JSON: \(error)")
    }
}
