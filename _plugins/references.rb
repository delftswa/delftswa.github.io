module Jekyll
  module Converters
    class Markdown < Converter
      alias old_convert convert
      @@refs_content = nil

      def convert(content)
        # read and cache content of _references.md
        if @@refs_content.nil?
          refs_path = File.join(@config["source"], "_references.md")
          @@refs_content = if File.exist?(refs_path) then File.read(refs_path)
                           else "" end

          # Remove comments from file.
          @@refs_content = @@refs_content.gsub(/^#[^\n]*\n/, '')
        end


        # append content of _references.md, whatever it is
        content += "\n" + @@refs_content

        old_convert(content)
      end
    end
  end
end
