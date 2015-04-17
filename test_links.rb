chapter_name = ARGV[0]

violating_links = []

def perform_check_on_path(path)
  p "checking #{path}"
  violating_links = []

  content = File.read(path)
  # Check for links with delftswa2014 in them
  res = content.to_enum(:scan, /\[.*\]\((.*delftswa2014.*)\)/).map { Regexp.last_match }
  violating_links += res.map { |match| { file: path, link: match[1] } } unless res.empty?

  # Check for non-absolute links
  res = content.to_enum(:scan, /\[.*\]\(((?!(https?)|\#|images).*)\)/).map { Regexp.last_match }
  violating_links += res.map { |match| { file: path, link: match[1] } } unless res.empty?

  return violating_links
end

Dir.glob("chapters/#{chapter_name}/**/*.md").each do |path|
  violating_links += perform_check_on_path(path)
end

p ""
p ""

if violating_links.empty?
  p "All looks well!"
else
  p "Seems like some links are pointing to non-accessible locations, ensure they are ok please"
  violating_links.each do |o|
    p "In file #{o[:file]} the following link was suspicious #{o[:link]}"
  end
end

