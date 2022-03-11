require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorDeezer'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp,a}'
  s.ios.deployment_target  = '12.0'
  s.dependency 'Capacitor'
  s.swift_version = '5.1'
  s.pod_target_xcconfig = {
     'DZR_SDK' => '$(PODS_TARGET_SRCROOT)/ios/Plugin/DeezerSDK',
     'LIBRARY_SEARCH_PATHS' => '$(DZR_SDK)',
     'USER_HEADER_SEARCH_PATHS' => '$(DZR_SDK)/include/**',
     'OTHER_LDFLAGS' => '-lDeezer -ObjC',
  }
  s.ios.framework = 'AVFoundation', 'MediaPlayer', 'SystemConfiguration', 'AudioToolbox', 'CoreAudio'
end
